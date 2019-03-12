import React, { Component } from 'react';
import Header from "../header/Header";
import axios from 'axios';
import CreateArtistForm from "../forms/CreateArtistForm";
import {Grid} from "semantic-ui-react";
import { Route } from "react-router-dom";
import ArtistsList from '../artistsList/ArtistsList';

export default class Home extends Component {
  constructor(props) {
    super(props);
    this.state = {
      currentUser: {

      }
    }
  }

  componentDidMount() {
    axios.get('/api/users/current')
      .then((response) => {
        this.setState({
          currentUser: response.data
        });
      });
  }

  render() {
    return (
      <div className={'home'}>
        <Header currentUser={this.state.currentUser}/>
        <Grid textAlign='center' style={{ height: '100%' }} verticalAlign='middle'>
          <Grid.Column style={{ maxWidth: 450 }}>
          </Grid.Column>
        </Grid>
      </div>
    );
  }
}