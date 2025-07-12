import { defineConfig } from 'eslint/config';
import simpleImportSort from 'eslint-plugin-simple-import-sort';
import { frontendEslintConfig, frontendIgnoredFiles } from './frontend/eslint-frontend.config.js';

const sharedConfig = {
  files: [],
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

sharedConfig.files = Array.from(new Set([...sharedConfig.files, ...frontendEslintConfig.files]));

export default defineConfig([
  frontendIgnoredFiles,
  sharedConfig,
  frontendEslintConfig,
]);
