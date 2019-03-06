import React, { Component } from 'react';
import { Button, Form, Grid, Header, Message, Segment } from "semantic-ui-react";
import { Link, withRouter } from "react-router-dom";
import axios from 'axios';

class SignUpForm extends Component {
  constructor(props) {
    super(props);

    this.state = {
      firstName: '',
      lastName: '',
      password: '',
      matchingPassword: '',
      email: ''
    };

    this.handleFormChange = this.handleFormChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleSubmit() {
    const { history } = this.props;

    axios.post('api/users/registration', {
      ...this.state
    }).then(response => {
      if (response.status === 200) {
        localStorage.setItem('auth', 'true');
        setTimeout(() => {
          history.push('/');
        }, 250);
      }
    });
  }

  handleFormChange(event, data) {
    switch (data.placeholder) {
      case 'First Name' : this.setState({
        ...this.state,
        firstName: data.value,
      }); break;
      case 'Last Name' : this.setState({
        ...this.state,
        lastName: data.value
      }); break;
      case 'E-mail address' : this.setState({
        ...this.state,
        email: data.value
      }); break;
      case 'Password' : this.setState({
        ...this.state,
        password: data.value
      }); break;
      case 'Confirm Password' : this.setState({
        ...this.state,
        matchingPassword: data.value
      });
    }
  }

  render() {
    return (
      <div className='login-form'>
        <style>{`
      body > div,
      body > div > div,
      body > div > div > div.login-form {
        height: 100%;
      }
    `}</style>
        <Grid textAlign='center' style={{ height: '100%' }} verticalAlign='middle'>
          <Grid.Column style={{ maxWidth: 450 }}>
            <Header as='h2' color='teal' textAlign='center'>
              Create An Account
            </Header>
            <Form size='large'>
              <Segment stacked>
                <Form.Input fluid icon='user' iconPosition='left' placeholder='First Name' onChange={this.handleFormChange} />
                <Form.Input fluid icon='user' iconPosition='left' placeholder='Last Name' onChange={this.handleFormChange}/>
                <Form.Input fluid icon='user' iconPosition='left' placeholder='E-mail address' onChange={this.handleFormChange} />
                <Form.Input
                  fluid
                  icon='lock'
                  iconPosition='left'
                  placeholder='Password'
                  type='password'
                  onChange={this.handleFormChange}
                />
                <Form.Input
                  fluid
                  icon='lock'
                  iconPosition='left'
                  placeholder='Confirm Password'
                  type='password'
                  onChange={this.handleFormChange}
                />

                <Button color='teal' fluid size='large' onClick={this.handleSubmit}>
                  Sign Up
                </Button>
              </Segment>
            </Form>
            <Message>
              Already have an account? <Link to='/login'>Login</Link>
            </Message>
          </Grid.Column>
        </Grid>
      </div>
    );
  }
}

export default withRouter(SignUpForm);