import React, { Component } from 'react';
import Header from "../header/Header";
import axios from 'axios';

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
      </div>
    );
  }
}