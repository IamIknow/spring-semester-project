import React, { useContext, useEffect, useState } from 'react';
import { Grid } from "semantic-ui-react";
import Header from "../header/Header";
import CreateArtistForm from "../forms/CreateArtistForm";
import * as API from '../../api/api';
import { AppDispatch } from "../../app";
import { ActionTypes } from '../../shared/ActionTypes';
import ArtistsListItem from "../artistsListItem/ArtistsListItem";

export default function ArtistList() {
  const { dispatch, state } = useContext(AppDispatch);
  const [ artists, setArtists ] = useState([]);

  useEffect(() => {
    API.getCurrentUser(response => {
      dispatch({
        type: ActionTypes.CURRENT_USER_FETCHED,
        currentUser: response.data
      });
    });
  }, []);

  useEffect(() => {
    API.getArtistsList(response => {
      setArtists(response.data);
    });
  }, []);

  const artistsList = artists.map((artist, index) => {
    return <ArtistsListItem key={index} artist={artist}/>
  });

  return (
    <>
      <Header currentUser={state.currentUser}/>
      <Grid textAlign='center' style={{ height: '100%' }} verticalAlign='middle'>
        <Grid.Column style={{ maxWidth: 450 }}>
          {
            state.currentUser && state.currentUser.role === 'ADMIN' &&
            <div className={'create-artist-form-container'}>
              <CreateArtistForm/>
            </div>
          }
          <div className={'artists-list'}>
            {artistsList}
          </div>
        </Grid.Column>
      </Grid>
    </>
  );
}
