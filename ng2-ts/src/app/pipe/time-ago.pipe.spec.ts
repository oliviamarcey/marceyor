import { TimeAgoPipe } from './time-ago.pipe';

describe('TimeAgoPipe', () => {

	let pipe = new TimeAgoPipe();

	let currentTime = new Date();
	let fiveMinAgo = new Date(currentTime.getFullYear(), currentTime.getMonth(), currentTime.getDate(),
							currentTime.getHours(), currentTime.getMinutes() - 5, 0, 0);

	let sevenHrsAgo = new Date(currentTime.getFullYear(), currentTime.getMonth(), currentTime.getDate(),
							currentTime.getHours() - 7, currentTime.getMinutes(), 0, 0);

	it('should be just now', () => {
		expect(pipe.transform(null)).toBe('Just now');
	});

	it('should be 0 seconds ago', () => {
		expect(pipe.transform(currentTime)).toBe('0 seconds ago');
	});

	it('should be 5 minutes ago', () => {
		expect(pipe.transform(fiveMinAgo)).toBe('5 minutes ago');
	});

	it('should be 7 hours ago', () => {
		expect(pipe.transform(sevenHrsAgo)).toBe('7 hours ago');
	});
});