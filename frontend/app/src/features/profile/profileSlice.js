import { createAsyncThunk, createSlice } from '@reduxjs/toolkit';
import api from '@src/api/api.js';

const fetchMyProfile = createAsyncThunk(
  'profile/fetchMyProfile',
  async (_, { rejectWithValue }) => {
    try {
      const { data } = await api.get('/authenticated/profile');
      return data;
    } catch (error) {
      const data = error.response?.data;
      return rejectWithValue(data?.details || data?.title);
    }
  },
);

const initialState = {
  profile: {},
  loading: false,
  error: '',
};

const profileSlice = createSlice({
  name: 'profile',
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchMyProfile.pending, (state) => {
        state.profile = {};
        state.loading = true;
        state.error = '';
      })
      .addCase(fetchMyProfile.fulfilled, (state, action) => {
        state.profile = action.payload;
        state.loading = false;
        state.error = '';
      })
      .addCase(fetchMyProfile.rejected, (state, action) => {
        state.profile = {};
        state.loading = false;
        state.error = action.payload;
      });
  },
});

export { fetchMyProfile };
export default profileSlice.reducer;
