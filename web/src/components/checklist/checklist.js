import React from 'react';
import CheckListItem from '../checklist-item/checklistItem';
import {Button} from 'react-bulma-components/full';
import {Hits, InstantSearch, SearchBox} from 'react-instantsearch-dom';

const CheckList = ({list, onPress, addNew}) => {
  const checkList = ({hit}) => (
    <CheckListItem item={hit} onPress={(id) => onPress(id)}/>
  );

  return (
     <div>
       <InstantSearch
         appId="AV5HLF6Z2Q"
         apiKey="af1ff1cbe35aac61ee4fa9622a1fd2ec"
         indexName="checklists"
       >
         <SearchBox />
         <Hits hitComponent={checkList} />
         {/* Search widgets will go there */}
       </InstantSearch>
       <Button color={'success'} onClick={() => addNew()}>ADD NEW LIST</Button>
     </div>
    );
};

export default CheckList;
