import api from '@src/api/api.js';
import {
  fetchMyProfileFailed,
  fetchMyProfileRequested,
  fetchMyProfileSucceeded,
} from '@src/features/profile/profileSlice.js';
import { call, put, takeLatest } from 'redux-saga/effects';

function* fetchMyProfile() {
  try {
    const response = yield call(api.get, '/authenticated/profile');
    yield put(fetchMyProfileSucceeded(response.data));
  } catch (error) {
    const data = error.response.data;
    yield put(fetchMyProfileFailed(data?.details || data?.title));
  }
}

export function* watchFetchMyProfile() {
  yield takeLatest(fetchMyProfileRequested.type, fetchMyProfile);
}
