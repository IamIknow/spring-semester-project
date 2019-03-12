import { ActionTypes } from "../shared/ActionTypes";

export default function appReducer(state, action) {
  switch (action.type) {
    case ActionTypes.AUTHORIZED:
      return { auth: true };
    case ActionTypes.UNAUTHORIZED:
      return { auth: false };
    case ActionTypes.CURRENT_USER_FETCHED:
      return {
        auth: true,
        currentUser: action.currentUser
      }
  }
}