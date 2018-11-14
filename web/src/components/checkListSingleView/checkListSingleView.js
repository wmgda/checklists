import React, {Component} from 'react';
import {Button, Heading} from 'react-bulma-components';

export default class CheckListSingleView extends Component {
  render() {
    const { item } = this.props;
    console.log(this.props);
    return (
      <div>
        <Heading>{item.title}</Heading>
        <Button
          color={'info'}
          onClick={() => this.props.onPress()}
        >
          GetBack
        </Button>
      </div>
    );
  }
};