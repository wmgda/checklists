import React, { Component } from 'react';
import './App.css';
import { fire } from './fire';
import CheckList from './components/checklist/checklist';
import {Button, Heading} from 'react-bulma-components/full';
import CheckListSingleView
  from './components/checkListSingleView/checkListSingleView';
import CheckListNew from './components/checkListNew/checkListNew';
import {Content, Image, Media} from 'react-bulma-components';
import algoliasearch from 'algoliasearch'

const algolia = algoliasearch(
  'AV5HLF6Z2Q',
  '04717f97300dd095d8358809373c61f0'
);

const index = algolia.initIndex('checklists');


class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      checklists: [],
      currentSelected: 'list',
      id: null,
      clearedCache: new Date(),
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
        this.setState({checklists: [...this.state.checklists, form]}, () => {
          console.log(this.state);
          form.objectId = form.id;
          index.addObject(form, () => {
            console.log('added');
            setTimeout(() => {
              this.setState({currentSelected: 'list'})
            }, 1000)
          })
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
    <CheckList clearedCache={this.state.clearedCache} list={this.state.checklists} onPress={(id) => {
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
