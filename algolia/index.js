const algoliasearch = require('algoliasearch');
const dotenv = require('dotenv');
const Firestore = require('@google-cloud/firestore');

dotenv.load();

const algolia = algoliasearch(
    process.env.ALGOLIA_APP_ID,
    process.env.ALGOLIA_API_KEY
);

const index = algolia.initIndex(process.env.ALGOLIA_INDEX_NAME);

const firestore = new Firestore({
    projectId: process.env.FIREBASE_PROJECT_ID,
    keyFilename: 'firebase-key.json',
    timestampsInSnapshots: true,
});
const collection = firestore.collection('checklists');

index.clearIndex();

collection.get().then(querySnapshot => {
    const records = [];

    querySnapshot.forEach(checklist => {
        let data = checklist.data();
        data.objectID = checklist.id;

        records.push(data);
    });

    index.saveObjects(records).then(() => {
        console.log('Checklists imported into Algolia', records);
    }).catch(error => {
        console.error('Error when importing contact into Algolia', error);
        process.exit(1);
    });
});
