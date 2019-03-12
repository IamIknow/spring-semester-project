import axios from 'axios';

function getCurrentUser(successCallback) {
  return axios.get('/api/users/current')
    .then((response) => successCallback(response));
}

function getArtistsList(successCallback) {
  return axios.get('/api/artists')
    .then((response) => successCallback(response));
}

export {
  getCurrentUser,
  getArtistsList
};