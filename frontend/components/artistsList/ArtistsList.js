import React, {Component} from 'react';
import axios from 'axios';
import {Grid, Segment} from "semantic-ui-react";
import Header from "../header/Header";

export default class ArtistsList extends Component {
  constructor(props) {
    super(props);
    this.state = {
      artists: [],
      currentUser: ''
    };
  }

  componentDidMount() {
    axios.get('/api/users/current')
      .then((response) => {
        this.setState({
          currentUser: response.data
        });
      });

    axios.get('/api/artists')
      .then(response => {
        this.setState({
          ...this.state,
          artists: response.data
        });
      });
  }

  render() {
    const artists = this.state.artists.map((artist, index) => {
      return (
        <div className={'artists-list__item'} key={index}>
          <Segment>{artist.name}</Segment>
        </div>
      );
    });

    return (
      <div>
        <Header currentUser={this.state.currentUser}/>
        <Grid textAlign='center' style={{ height: '100%' }} verticalAlign='middle'>
          <Grid.Column style={{ maxWidth: 450 }}>
            <div className={'artists-list'}>
              {artists}
            </div>
          </Grid.Column>
        </Grid>
      </div>
    );
  }
}