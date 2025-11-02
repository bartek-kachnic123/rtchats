import { ValidateEnv } from '@julr/vite-plugin-validate-env';
import react from '@vitejs/plugin-react';
import path from 'path';
import { fileURLToPath } from 'url';
import { defineConfig } from 'vite';

import { environmentValidationConfig } from './config/environment-validation.config.js';
import vitestConfig from './config/vitest.config.js';

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

// https://vite.dev/config/
export default defineConfig({
  plugins: [react(), ValidateEnv(environmentValidationConfig)],
  ...vitestConfig,
  resolve: {
    alias: {
      '@src': path.resolve(__dirname, 'src'),
    },
  },
  server: {
    proxy: {
      '/api': {
        target: 'http://localhost:8080',
        changeOrigin: true,
      },
    },
  },
});
