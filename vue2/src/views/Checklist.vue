<template>
  <div class="card" style="margin-top: 20px">
    <div class="card-content">
    <h1 class="title">{{ checklist.title }}</h1>

    <div v-for="item in checklist.items" :key="item.id" class="field">
      <b-checkbox>{{ item.title }}</b-checkbox>
    </div>
    </div>
  </div>
</template>

<script>
const firebase = require('firebase');
require("firebase/firestore");

firebase.initializeApp({
  apiKey: 'AIzaSyCN9RzptHJW-lZOvzpFlkJ-5HospfbtdL8',
  authDomain: 'listy-9ae06.firebaseapp.com',
  projectId: 'listy-9ae06',
  timestampsInSnapshots: true,
});

export default {
  data: function () {
    return { checklist: {} };
  },
  mounted: function() {
    var db = firebase.firestore();

    db.collection("checklists").doc(this.$route.params.id).get().then((doc) => {
        this.checklist = doc.data();
    });
  }
}
</script>

