import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  authenticated: false,
  loading: false,
  error: '',
};

const authSlice = createSlice({
  name: 'auth',
  initialState,
  reducers: {
    loginRequested(state) {
      state.loading = true;
      state.error = '';
    },
    loginSucceeded(state) {
      state.authenticated = true;
      state.loading = false;
      state.error = '';
    },
    loginFailed(state, action) {
      state.authenticated = false;
      state.loading = false;
      state.error = action.payload;
    },
    logoutRequested(state) {
      state.loading = true;
      state.error = '';
    },
    loggedOut(state) {
      state.authenticated = false;
      state.loading = false;
      state.error = '';
    },
  },
});

export const {
  loginRequested,
  loginSucceeded,
  loginFailed,
  logoutRequested,
  loggedOut,
} = authSlice.actions;

export default authSlice.reducer;
