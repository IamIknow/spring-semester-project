import React, { useContext } from 'react';
import PropTypes from 'prop-types';
import { AppDispatch } from "../../app";
import { Button, Segment } from "semantic-ui-react";

const propTypes = {
  album: PropTypes.object,
  onDelete: PropTypes.func
};

function AlbumsListItem ({ album, onDelete }) {
  const { state } = useContext(AppDispatch);

  return (
    <div className={'albums-list__item'}>
      <Segment>
        {
          state.currentUser && state.currentUser.role === 'ADMIN' &&
          <Button onClick={() => onDelete(album)}>Delete</Button>
        }
        <div className={'albums-list__item__name'}><b>Album name: </b>{album.name}</div>
        <div className={'albums-list__item__year'}><b>Album release date: </b>{album.releaseDate}</div>
        <div className={'albums-list__item__genre'}><b>Album genre: </b>{album.genre}</div>
      </Segment>
    </div>
  );
}
AlbumsListItem.propTypes = propTypes;

export default AlbumsListItem;