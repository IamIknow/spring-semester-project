import React, { useState, useEffect, useContext } from 'react';
import Header from "../header/Header";
import * as API from '../../api/api';
import { Grid } from "semantic-ui-react";
import { withRouter } from "react-router-dom";
import { AppDispatch } from "../../app";
import CreateAlbumForm from "../forms/CreateAlbumForm";
import { getAlbumsForArtist } from "../../api/api";
import AlbumsListItem from "../albumsListItem/AlbumsListItem";
import { deleteAlbum } from "../../api/api";

function ArtistPage (props) {
  const { id } = props.match.params;

  const { state } = useContext(AppDispatch);

  const [artist, setArtist] = useState({});
  const [albums, setAlbums] = useState([]);

  useEffect(() => {
    API.getArtistById(id, response => {
      setArtist(response.data);
    }).then(() => {
      getAlbumsForArtist(id, response => {
        setAlbums(response.data);
      });
    });
  }, [id]);

  const handleAddAlbum = ({ data }) => {
    setAlbums(albums.concat([data]));
  };

  const handleDeleteAlbum = album => {
    deleteAlbum(album.id, () => {
      setAlbums(albums.filter(alb => alb.id !== album.id));
    });
  };

  const albumsList = albums.map((album, index) => <AlbumsListItem key={index} album={album} onDelete={handleDeleteAlbum}/>);

  return (
    <>
      <Header currentUser={state.currentUser}/>
      <Grid textAlign='center' style={{ height: '100%' }} verticalAlign='middle'>
        <Grid.Column style={{ maxWidth: 450 }}>
          <div className={'artist-info'}>
            <h2>{artist.name}</h2>
          </div>
          {
            state.currentUser && state.currentUser.role === 'ADMIN' &&
            <CreateAlbumForm artist={artist} onAdd={handleAddAlbum}/>
          }
          <div className={'albums-list'}>
            {albumsList}
          </div>
        </Grid.Column>
      </Grid>
    </>
  );
}

export default withRouter(ArtistPage);