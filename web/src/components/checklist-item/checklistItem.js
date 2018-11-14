import React from 'react';

const CheckListItem = ({item}) => {
    return (
      <div>{item.id} === {item.title}</div>
    );
};

export default CheckListItem;

