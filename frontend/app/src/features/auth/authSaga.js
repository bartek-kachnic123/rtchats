import api from '@src/api/api.js';
import { call, put, takeLatest } from 'redux-saga/effects';

import { loginFailed, loginRequested, loginSucceeded } from './authSlice';

function* loginSaga(action) {
  try {
    yield call(api.post, '/login', action.payload);
    yield put(loginSucceeded());
  } catch (error) {
    if (error.response && error.response.status === 401) {
      yield put(loginFailed('Invalid credentials'));
    } else {
      yield put(loginFailed('Something went wrong. Please try again.'));
    }
  }
}

export function* watchLogin() {
  yield takeLatest(loginRequested.type, loginSaga);
}
