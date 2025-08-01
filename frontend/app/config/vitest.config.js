import { defineConfig } from 'vitest/config';

export default defineConfig({
  test: {
    environment: 'jsdom',
    setupFiles: ['./tests/vitest-cleanup-after-each.js'],

    coverage: {
      provider: 'istanbul',
      reporter: ['text', 'lcov', 'json-summary'],
    },
  },
});
