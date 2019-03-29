import axios from 'axios';

function getCurrentUser(successCallback) {
  return axios.get('/api/users/current')
    .then((response) => successCallback(response));
}

function getArtistsList(successCallback) {
  return axios.get('/api/artists')
    .then((response) => successCallback(response));
}

function getArtistById(id, successCallback) {
  return axios.get(`/api/artists/${id}`)
    .then((response) => successCallback(response));
}

function deleteArtistById(id, successCallback) {
  return axios.delete(`/api/artists/${id}`)
    .then((response) => successCallback(response));
}

function postAlbum(artistId, data, successCallback) {
  return axios.post(`/api/artists/${artistId}/albums`, data)
    .then((response) => successCallback(response));
}

function getAlbumsForArtist(artistId, successCallback) {
  return axios.get(`/api/artists/${artistId}/albums`)
    .then((response) => successCallback(response));
}

function deleteAlbum(albumId, successCallback) {
  return axios.delete(`/api/albums/${albumId}`)
    .then((response) => successCallback(response));
}

export {
  getCurrentUser,
  getArtistsList,
  getArtistById,
  deleteArtistById,
  postAlbum,
  getAlbumsForArtist,
  deleteAlbum
};