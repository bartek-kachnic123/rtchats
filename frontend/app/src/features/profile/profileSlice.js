import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  profile: {},
  loading: false,
  error: '',
};

const profileSlice = createSlice({
  name: 'profile',
  initialState,
  reducers: {
    fetchMyProfileRequested(state) {
      state.profile = {};
      state.loading = true;
      state.error = '';
    },
    fetchMyProfileSucceed(state, action) {
      state.profile = action.payload;
      state.loading = false;
      state.error = '';
    },
    fetchMyProfileFailed(state, action) {
      state.profile = {};
      state.loading = false;
      state.error = action.payload;
    },
    myProfileCleared(state) {
      state.profile = {};
    },
  },
});

export const {
  fetchMyProfileRequested,
  fetchMyProfileSucceed: fetchMyProfileSucceeded,
  fetchMyProfileFailed,
  myProfileCleared,
} = profileSlice.actions;

export default profileSlice.reducer;
