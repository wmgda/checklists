import React from 'react';
import {Box, Content, Media} from 'react-bulma-components';
import {style} from './style';

const CheckListItem = ({item}) => {
    return (
      <div style={style}>
        <p>
          <small>{item.title}</small>
        </p>
      </div>
    );
};

export default CheckListItem;

