import { configureStore } from '@reduxjs/toolkit';
import authReducer from '@src/features/auth/authSlice.js';
import registerReducer from '@src/features/registeruser/registerSlice.js';

export default configureStore({
  reducer: {
    register: registerReducer,
    auth: authReducer,
  },
});
