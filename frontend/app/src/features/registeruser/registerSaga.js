import api from '@src/api/api.js';
import {
  registerFailed,
  registerRequested,
  registerSucceeded,
} from '@src/features/registeruser/registerSlice.js';
import { call, put, takeLatest } from 'redux-saga/effects';

function* registerSaga(action) {
  try {
    yield call(api.post, '/users', action.payload);
    yield put(registerSucceeded());
  } catch (error) {
    const data = error.response.data;
    yield put(
      registerFailed(
        data?.errors
          ? data.errors
          : [
              {
                field: null,
                defaultMessage: data?.detail || data?.title || error.message,
              },
            ],
      ),
    );
  }
}

export function* watchRegister() {
  yield takeLatest(registerRequested.type, registerSaga);
}
