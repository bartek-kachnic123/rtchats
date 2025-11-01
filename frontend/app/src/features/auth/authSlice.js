import { createAsyncThunk, createSlice } from '@reduxjs/toolkit';
import api from '@src/api/api.js';

const loginUser = createAsyncThunk(
  'auth/loginUser',
  async (data, { rejectWithValue }) => {
    try {
      await api.post('/login', data);
    } catch (error) {
      if (error.response && error.response.status === 401) {
        return rejectWithValue('Invalid credentials');
      }
      return rejectWithValue('Something went wrong. Please try again.');
    }
  },
);

const initialState = {
  isAuthenticated: false,
  loading: false,
  error: '',
};
const authSlice = createSlice({
  name: 'auth',
  initialState,
  reducers: {
    loggedOut(state) {
      state.isAuthenticated = false;
      state.loading = false;
      state.error = '';
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(loginUser.pending, (state) => {
        state.loading = true;
        state.error = '';
      })
      .addCase(loginUser.fulfilled, (state) => {
        state.loading = false;
        state.isAuthenticated = true;
      })
      .addCase(loginUser.rejected, (state, action) => {
        state.loading = false;
        state.isAuthenticated = false;
        state.error = action.payload;
      });
  },
});

export const { loggedOut } = authSlice.actions;
export { loginUser };
export default authSlice.reducer;
