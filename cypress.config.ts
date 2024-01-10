import { defineConfig } from "cypress";
import vitePreprocessor from 'cypress-vite'

export default defineConfig({
  e2e: {
    setupNodeEvents(on, config) {
      on("file:preprocessor", vitePreprocessor())
      return config
    },
    reporter: 'mochawesome',
    experimentalRunAllSpecs: true,
    specPattern: 'cypress/e2e/**/*.spec.ts',
    supportFile: "cypress/support/e2e.ts",
    watchForFileChanges: false,
    excludeSpecPattern: [
      '**/__snapshots__/*',
      '**/__image_snapshots__/*',
    ],
  },
});
