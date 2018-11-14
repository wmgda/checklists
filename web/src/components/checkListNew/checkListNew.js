import React, {Component} from 'react';
import {Button, Heading, Control, Field, Input, Label} from 'react-bulma-components/full';

export default class CheckListNew extends Component {

  constructor() {
    super();
    this.state = {
      form: {
        title: '',
        items: [
          {title: ''}
        ]
      }
    }
  }

  handleChange = e => {
    this.setState({form: {
        ...this.state.form,
        title: e.target.value
      }})
  };

  handleSave = () => {
    this.props.save(this.state.form);
  };

  handleClick = () => {
    const items = this.state.form.items;
    items.push({title: ''});
    this.setState({form: {
        ...this.state.form,
        items,
      }})
  };

  render() {
    const items = () => {
      const items = this.state.form.items.map((el, index) => (
        <div key={index} style={{display: 'flex', flexDirection: 'column'}}>
          <label>{index + 1}</label>
          <input type="text" value={el.title} onChange={(e) => {
            const mapped = this.state.form.items.map((el, elIndex) => {
              if (index === elIndex) {
                return {title: e.target.value};
              }
              return el;
            });
            this.setState({form: {
                ...this.state.form,
                items: mapped,
              }})
          }}/>
        </div>
      ));
      return items;
    };
    return (
      <div>
        <Button

          color={'info'}
          onClick={() => this.props.return()}
        >
          GetBack
        </Button>
        <form style={{margin: '10px', width: '30%', display: 'flex', flexDirection: 'column'}}>
          <div style={{flexDirection: 'column', display: 'flex'}} >
            <label htmlFor="title">Title</label>
            <input type="text" onChange={this.handleChange} value={this.state.form.title}/>
          </div>
          <Heading> Items </Heading>
          {items()}
        </form>
        <div style={{display: 'flex', flexDirection: 'column', width: '30%', margin: '10px'}}>
          <Button
            style={{marginBottom: '10px'}}
            onClick={this.handleClick}
          >Add new item</Button>
          <Button
            color={'success'}
            onClick={this.handleSave}
          >Save</Button>
        </div>

      </div>
    );
  }
};