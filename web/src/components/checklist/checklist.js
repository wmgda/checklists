import React from 'react';
import CheckListItem from '../checklist-item/checklistItem';

const CheckList = ({list}) => {
  const listView = list.map((item, index) => (
    <CheckListItem item={item} key={index}/>
  ));

  return (
     <div>{listView}</div>
    );
};

export default CheckList;
