import React from 'react';
import CheckListItem from '../checklist-item/checklistItem';
import {Button} from 'react-bulma-components/full';

const CheckList = ({list, onPress, addNew}) => {
  const listView = list.map((item, index) => (
    <CheckListItem item={item} key={index} onPress={(id) => onPress(id)}/>
  ));

  return (
     <div>
       {listView}
       <Button color={'success'} onClick={() => addNew()}>ADD NEW LIST</Button>
     </div>
    );
};

export default CheckList;
