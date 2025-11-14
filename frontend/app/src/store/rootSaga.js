import { watchLogin, watchLogout } from '@src/features/auth/authSaga.js';
import { all } from 'redux-saga/effects';

export default function* rootSaga() {
  yield all([watchLogin(), watchLogout()]);
}
