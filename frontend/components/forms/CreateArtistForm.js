import React, { Component } from 'react';
import { Button, Form, Message, Segment } from "semantic-ui-react";
import axios from 'axios';

export default class CreateArtistForm extends Component {
  constructor(props) {
    super(props);

    this.state = {
      message: null,
      name: '',
      error: ''
    };

    this.handleFormChange = this.handleFormChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleFormChange(event, data) {
    this.setState({
      ...this.state,
      name: data.value,
    });
  }

  handleSubmit() {
    const { artistAdditionCallback } = this.props;

    if (!this.state.name.length) {
      this.setState({
        ...this.state,
        error: 'name is required'
      });

      return;
    } else {
      this.setState({
        ...this.state,
        error: ''
      });
    }

    axios.post('/api/artists', {
      ...this.state
    }).then(response => {
      const name = response.data.name;
      this.setState({
        ...this.state,
        message: `Artist ${name} successfully submitted`
      });
      artistAdditionCallback(response.data.name);
    });
  }

  render() {
    return (
      <div className={'create-artist-form'}>
        <Form size='large'>
          <Segment>
            <Form.Input placeholder='Artist Name' onChange={this.handleFormChange} error={!!this.state.error.length}/>
            <Button color='teal' fluid size='large' onClick={this.handleSubmit}>
              Submit
            </Button>
          </Segment>
          <Message>
            {this.state.message ? this.state.message : ''}
          </Message>
        </Form>
      </div>
    )
  }
}