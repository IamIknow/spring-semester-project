import React, { useContext } from 'react';
import { Button, Segment } from "semantic-ui-react";
import { withRouter } from "react-router-dom";
import { AppDispatch } from "../../app";
import { deleteArtistById } from "../../api/api";
import PropTypes from 'prop-types';

const propTypes = {
  artist: PropTypes.object,
  onDelete: PropTypes.func
};

function ArtistsListItem (props) {
  const { artist, onDelete } = props;
  const { dispatch, state } = useContext(AppDispatch);

  const deleteHandler = (event) => {
    event.stopPropagation();
    deleteArtistById(artist.id, () => {
      onDelete(artist.id);
    });
  };

  return (
    <div className={'artists-list__item'}
         onClick={() => props.history.push(`/artists/${artist.id}`)}
    >
      <Segment>
        {
          state.currentUser && state.currentUser.role === 'ADMIN' &&
          <div className={'artist-list__item__controls'}>
            <Button onClick={deleteHandler}>
              Delete
            </Button>
          </div>
        }
        {artist.name}
      </Segment>
    </div>
  );
}

export default withRouter(ArtistsListItem);