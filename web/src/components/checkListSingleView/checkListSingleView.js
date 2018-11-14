import React, {Component} from 'react';
import {Button, Heading} from 'react-bulma-components';
import CheckListNew from '../checkListNew/checkListNew';

export default class CheckListSingleView extends Component {

  constructor(props) {
    super(props);
    this.state = {
      form: props.item,
    };
    console.log(this.state);
  }

  handleChange = e => {
    this.setState({form: {
        ...this.state.form,
        title: e.target.value
      }})
  };

  render() {
    const { item } = this.props;
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
        <Heading>{item.title}</Heading>
        <form style={{margin: '10px', width: '30%', display: 'flex', flexDirection: 'column'}}>
          <div style={{flexDirection: 'column', display: 'flex'}} >
            <label htmlFor="title">Title</label>
            <input type="text" onChange={this.handleChange} value={this.state.form.title}/>
          </div>
          <Heading> Items </Heading>
          {items()}
        </form>
        <Button
          color={'success'}
          onClick={() => this.props.edit(this.state.form)}
        >
          Save
        </Button>
      </div>
    );
  }
};