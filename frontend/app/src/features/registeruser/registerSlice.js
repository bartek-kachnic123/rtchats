import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  loading: false,
  errors: [],
  status: 'idle',
};

const registerSlice = createSlice({
  name: 'register',
  initialState,
  reducers: {
    registerRequested(state) {
      state.loading = true;
      state.errors = [];
      state.status = 'loading';
    },
    registerSucceeded(state) {
      state.loading = false;
      state.errors = [];
      state.status = 'succeeded';
    },
    registerFailed(state, action) {
      state.loading = false;
      state.errors = action.payload;
      state.status = 'failed';
    },
    registerResetted(state) {
      state.status = 'idle';
    },
  },
});

export const {
  registerRequested,
  registerSucceeded,
  registerFailed,
  registerReset,
} = registerSlice.actions;

export default registerSlice.reducer;
