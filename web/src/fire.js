import firebase from 'firebase'
const config = {
    apiKey: "AIzaSyCN9RzptHJW-lZOvzpFlkJ-5HospfbtdL8",
    authDomain: "listy-9ae06.firebaseapp.com",
    databaseURL: "https://listy-9ae06.firebaseio.com",
    projectId: "listy-9ae06",
    storageBucket: "listy-9ae06.appspot.com",
    messagingSenderId: "793699055744"
};
export const fire = firebase.initializeApp(config);
