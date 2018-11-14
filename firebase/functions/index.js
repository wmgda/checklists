const functions = require('firebase-functions');
const algoliasearch = require('algoliasearch');

// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions
//
// exports.helloWorld = functions.https.onRequest((request, response) => {
//  response.send("Hello from Firebase!");
// });

const ALGOLIA_ID = functions.config().algolia.app_id;
const ALGOLIA_ADMIN_KEY = functions.config().algolia.api_key;

const ALGOLIA_INDEX_NAME = 'checklists';
const client = algoliasearch(ALGOLIA_ID, ALGOLIA_ADMIN_KEY);

exports.onChecklistCreated = functions.firestore.document('checklists/{checklistId}').onCreate((snap, context) => {
    const checklist = snap.data();

    checklist.objectID = context.params.checklistId;

    const index = client.initIndex(ALGOLIA_INDEX_NAME);
    return index.saveObject(note);
});

exports.onChecklistUpdated = functions.firestore.document('checklists/{checklistId}').onUpdate((snap, context) => {
    const checklist = snap.data();

    checklist.objectID = context.params.checklistId;

    const index = client.initIndex(ALGOLIA_INDEX_NAME);
    return index.saveObject(note);
});
