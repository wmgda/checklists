import React, { Component } from 'react';
import './App.css';
import { fire } from './fire';
import CheckList from './components/checklist/checklist';
import {Button, Heading} from 'react-bulma-components/full';
import CheckListSingleView
  from './components/checkListSingleView/checkListSingleView';
import CheckListNew from './components/checkListNew/checkListNew';
import {Content, Image, Media} from 'react-bulma-components';

class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      checklists: [],
      currentSelected: 'new',
      id: null,
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

  provide = (viewType, opt) => {
    let viewFactory = null;

    switch (viewType) {
      case 'list':
        viewFactory = this.checkList;
        break;

      case 'single':
        viewFactory = this.checkListSingle;
        break;

      case 'new':
        viewFactory = this.checkListNew;
        break;

      default:
        viewFactory = this.checkList;
    }
    return viewFactory(opt);
  };

  save = (form) => {
    const r = Math.random().toString(36).substring(7);
    form.id = r;
    const db = fire.firestore();
    db.collection("/checklists").add(form)
      .then(() => {
        this.setState({currentSelected: 'list', checklists: [...this.state.checklists, form]}, () => {
          console.log(this.state);
        });
      });
  };

  checkListNew = () => {
    return (
      <CheckListNew save={this.save} return={()=>{
        this.setState({currentSelected: 'list'})
      }}/>
    );
  };

  checkList = (opt) => (
    <CheckList list={this.state.checklists} onPress={(id) => {
      console.log(id);
      this.setState({currentSelected: 'single', id})
    }}
    addNew={() => {
      this.setState({currentSelected: 'new'})
    }}/>
  );

  checkListSingle = (opt) => {
    const item = this.state.checklists.find(item => item.id === this.state.id);
    return (
      <CheckListSingleView item={item}  onPress={() => {
        this.setState({currentSelected: 'list', id: null})
      }}/>
    );
  }

  render() {
    return (
      <div className="App">
        <div style={{display: 'flex', flexDirection: 'row', margin: '10px'}}>
          <img style={{width: '250px', height: '200px'}} src="https://i.imgur.com/PSs1AmH.png" />
        </div>
        {this.provide(this.state.currentSelected)}
      </div>
    );
  }
}

export default App;
