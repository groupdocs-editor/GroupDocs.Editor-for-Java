module.exports = {
  name: 'editor',
  preset: '../../jest.config.js',
  coverageDirectory: '../../coverage/apps/editor',
  snapshotSerializers: [
    'jest-preset-angular/AngularSnapshotSerializer.js',
    'jest-preset-angular/HTMLCommentSerializer.js'
  ]
};
