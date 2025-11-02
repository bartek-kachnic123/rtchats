import { createAsyncThunk, createSlice } from '@reduxjs/toolkit';
import api from '@src/api/api.js';

const registerUser = createAsyncThunk(
  'register/registerUser',
  async (userData, { rejectWithValue }) => {
    try {
      await api.post('/users', userData);
    } catch (error) {
      const data = error.response?.data;
      return rejectWithValue(
        data?.errors
          ? data.errors
          : [
              {
                field: null,
                defaultMessage: data?.detail || data?.title || error.message,
              },
            ],
      );
    }
  },
);

const initialState = {
  loading: false,
  errors: [],
};

const registerSlice = createSlice({
  name: 'register',
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(registerUser.pending, (state) => {
        state.loading = true;
        state.errors = [];
      })
      .addCase(registerUser.fulfilled, (state) => {
        state.loading = false;
        state.errors = [];
      })
      .addCase(registerUser.rejected, (state, action) => {
        state.loading = false;
        state.errors = action.payload;
      });
  },
});

export { registerUser };
export default registerSlice.reducer;
