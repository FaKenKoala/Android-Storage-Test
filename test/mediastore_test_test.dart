import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mediastore_test/mediastore_test.dart';

void main() {
  const MethodChannel channel = MethodChannel('mediastore_test');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await MediastoreTest.platformVersion, '42');
  });
}
