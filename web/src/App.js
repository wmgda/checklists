import React, { Component } from 'react';
import './App.css';
import { fire } from './fire';
import CheckList from './components/checklist/checklist';
import {Heading} from 'react-bulma-components';

class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      checklists: [],
    }
  }

  componentDidMount() {
    const db = fire.firestore();
    db.settings({
      timestampsInSnapshots: true
    });
    const checklistsRef = db.collection('/checklists');
    checklistsRef.get()
      .then((data) => {
        data.forEach(val => {
          const data = val.data();
          console.log(data);
          this.setState({ checklists: [...this.state.checklists, data]});
        })
      })
  }

  render() {
    const {checklists} = this.state;
    return (
      <div className="App">
        <Heading>Title</Heading>
        <CheckList list={checklists}/>
      </div>
    );
  }
}

export default App;
