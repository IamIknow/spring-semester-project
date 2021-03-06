import React, { Component } from 'react';
import { Container, Dropdown, Menu, } from 'semantic-ui-react'
import { Link, withRouter } from "react-router-dom";
import axios from 'axios';

class Header extends Component {
  constructor(props) {
    super(props);

    this.handleLogout = this.handleLogout.bind(this);
  }

  handleLogout(e) {
    e.preventDefault();
    const { history } = this.props;

    axios.post('/api/logout')
      .then(() => {
        localStorage.removeItem('auth');
        history.push('/login');
      });
  }

  render() {
    const { firstName, lastName } = this.props.currentUser;

    return (
      <div className={'header'}>
        <Menu inverted>
          <Container>
            <Link to={'/'}>
              <Menu.Item header>
                Music Library App
              </Menu.Item>
            </Link>

            <Link to={'/artists'}>
              <Menu.Item>
                Artists
              </Menu.Item>
            </Link>

            <Dropdown item simple style={{ alignSelf: 'flex-end' }} text={`${firstName} ${lastName}`}>
              <Dropdown.Menu>
                <Dropdown.Item onClick={this.handleLogout}>Logout</Dropdown.Item>
              </Dropdown.Menu>
            </Dropdown>
          </Container>
        </Menu>
      </div>
    );
  }
}

export default withRouter(Header);