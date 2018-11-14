import React from 'react';
import {Card, Box, Content, Media} from 'react-bulma-components';
import {style} from './style';

const CheckListItem = ({item}) => {
    return (
      <Card style={style}>
        <Card.Content>
          <p class="title">
              { item.title } 
            </p>
            <p class="subtitle">
              { item.items.slice(0, 3).map(function(item) { return item.title }).join(', ') }...
            </p>
        </Card.Content>
      </Card>
    );
};

export default CheckListItem;

