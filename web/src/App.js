import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import { fire } from './fire';

class App extends Component {

  componentDidMount() {
    console.log('xd');
    const db = fire.firestore();
    db.settings({
      timestampsInSnapshots: true
    });
    const userRef = db.collection('/checklists');
    console.log(userRef);
    userRef.get()
      .then((data) => {
        console.log(data.docs);
        data.forEach(val => {
          console.log(val);
        })
      })
  }

  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <p>
            Edit <code>src/App.js</code> and save to reload.
          </p>
          <a
            className="App-link"
            href="https://reactjs.org"
            target="_blank"
            rel="noopener noreferrer"
          >
            Learn React
          </a>
        </header>
      </div>
    );
  }
}

export default App;
