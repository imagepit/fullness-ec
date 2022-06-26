var gulp = require("gulp");
var sass = require('gulp-sass')(require('sass'));
var plumber = require("gulp-plumber");
var notify = require("gulp-notify");
var pug = require("gulp-pug");
// var rename = require("gulp-rename");
var autoprefixer = require('gulp-autoprefixer');
var exec = require('child_process').exec;

//sassとpugの監視をして変換処理させる
gulp.task('watch', () => {
    gulp.watch('./sass/**', gulp.task('sass'));
    gulp.watch('./pug/**', gulp.task('pug'));
    gulp.watch('./pug/**', gulp.task('pug'));
    gulp.watch('./src/main/resources/templates/**', gulp.task('reload'));
    gulp.watch('./src/main/resources/static/css/**', gulp.task('reload'));
});

//pugをhtmlに変換
gulp.task("pug", () => {
    var option = { pretty: true }
    return gulp.src(["./pug/**/*.pug", '!./pug/**/_*.pug'])
        .pipe(plumber({
            errorHandler: notify.onError("Error: <%= error.message %>")
        }))
        .pipe(pug(option))
        .pipe(gulp.dest("./src/main/resources/templates/"))
});

//sassをcssに変換
gulp.task("reload", () => {
    return exec('./gradlew processResources');
});

gulp.task("sass", () => {
    return gulp.src(["./sass/**/*.scss", "!./sass/**/_*.scss"])
        .pipe(plumber({
            errorHandler: notify.onError("Error: <%= error.message %>")
        }))
        .pipe(sass({ outputStyle: 'compressed' }))
        .pipe(autoprefixer({ grid: true }))
        .pipe(gulp.dest("./src/main/resources/static/css/"))
});

gulp.task('default', gulp.series(gulp.parallel('sass', 'pug', 'watch'), function (done) {
    done();
}));