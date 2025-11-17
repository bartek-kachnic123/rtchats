import { configureStore } from '@reduxjs/toolkit';
import authReducer from '@src/features/auth/authSlice.js';
import profileReducer from '@src/features/profile/profileSlice.js';
import registerReducer from '@src/features/registeruser/registerSlice.js';
import rootSaga from '@src/store/rootSaga.js';
import createSagaMiddleware from 'redux-saga';

const sagaMiddleware = createSagaMiddleware();

const store = configureStore({
  reducer: {
    register: registerReducer,
    auth: authReducer,
    profile: profileReducer,
  },
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({ thunk: true }).concat(sagaMiddleware),
});

sagaMiddleware.run(rootSaga);

export default store;
