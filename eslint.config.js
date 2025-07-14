import js from '@eslint/js';
import { defineConfig, globalIgnores } from 'eslint/config';
import prettier from 'eslint-config-prettier';
import simpleImportSort from 'eslint-plugin-simple-import-sort';

import {
  appIgnoredFiles,
  eslintAppConfig,
} from './frontend/app/eslint-app.config.js';
import { eslintDevtoolsConfig } from './frontend/devtools/eslint-devtools.config.js';

const rootConfig = {
  files: ['*.js'],
  extends: [js.configs.recommended, prettier],
};

const sharedConfig = {
  files: Array.from(
    new Set([
      ...rootConfig.files,
      ...eslintAppConfig.files,
      ...eslintDevtoolsConfig.files,
    ]),
  ),
  languageOptions: {
    ecmaVersion: 2020,
    parserOptions: {
      ecmaVersion: 'latest',
      sourceType: 'module',
    },
  },
  plugins: {
    'simple-import-sort': simpleImportSort,
  },
  rules: {
    'no-unused-vars': ['error', { varsIgnorePattern: '^[A-Z_]' }],
    'simple-import-sort/imports': 'error',
    'simple-import-sort/exports': 'error',
  },
};

export default defineConfig([
  globalIgnores(appIgnoredFiles),
  sharedConfig,
  rootConfig,
  eslintAppConfig,
  eslintDevtoolsConfig,
]);
