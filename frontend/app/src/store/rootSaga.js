import { watchLogin, watchLogout } from '@src/features/auth/authSaga.js';
import { watchFetchMyProfile } from '@src/features/profile/myProfileSaga.js';
import { watchRegister } from '@src/features/registeruser/registerSaga.js';
import { all } from 'redux-saga/effects';

export default function* rootSaga() {
  yield all([
    watchLogin(),
    watchLogout(),
    watchFetchMyProfile(),
    watchRegister(),
  ]);
}
