import React from 'react';
import { Segment } from "semantic-ui-react";
import { withRouter } from "react-router-dom";

function ArtistsListItem (props) {
  const { artist } = props;

  return (
    <div className={'artists-list__item'}
         onClick={() => props.history.push('/')}
    >
      <Segment>{artist.name}</Segment>
    </div>
  );
}

export default withRouter(ArtistsListItem);