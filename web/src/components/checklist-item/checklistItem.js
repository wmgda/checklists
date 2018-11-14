import React from 'react';
import {Box, Content, Media} from 'react-bulma-components';
import {style} from './style';

const CheckListItem = ({item, onPress}) => {
    return (
      <div style={style} onClick={() => onPress(item.id)}>
        <Box>
          <Media>
            <Media.Item>
              <Content>
                <p>
                  <strong>{item.id}</strong> <small>{item.title}</small> <small>{item.items.length}</small>
                </p>
              </Content>
            </Media.Item>
          </Media>
        </Box>
      </div>
    );
};

export default CheckListItem;

