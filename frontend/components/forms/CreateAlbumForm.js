import React, { Component } from 'react';
import { Button, Form, Segment } from "semantic-ui-react";
import { postAlbum } from "../../api/api";

export default class CreateAlbumForm extends Component {
  constructor(props) {
    super(props);

    this.state = {
      message: null,
      name: '',
      year: '',
      genre: ''
    };

    this.handleFormChange = this.handleFormChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleFormChange(event, data) {
    switch (data.placeholder) {
      case 'name' :
        this.setState({
          ...this.state,
          name: data.value,
        });
        break;
      case 'year' :
        this.setState({
          ...this.state,
          year: data.value
        });
        break;
      case 'genre' :
        this.setState({
          ...this.state,
          genre: data.value
        });
        break;
    }
  }

  handleSubmit() {
    const { artist, onAdd } = this.props;

    const albumData = {
      name: this.state.name,
      releaseDate: this.state.year,
      genre: this.state.genre
    };

    postAlbum(artist.id, albumData, onAdd);
  }

  render() {
    return (
      <div className={'create-album-form'}>
        <Form size='large'>
          <Segment stacked>
            <h2>Submit an album</h2>
            <Form.Input required placeholder='name' onChange={this.handleFormChange} />
            <Form.Input required placeholder='year' onChange={this.handleFormChange}/>
            <Form.Input required placeholder='genre' onChange={this.handleFormChange} />
            <Button color='teal' fluid size='large' onClick={this.handleSubmit}>
              Submit
            </Button>
          </Segment>
        </Form>
      </div>
    );
  }
}