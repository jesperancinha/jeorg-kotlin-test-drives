#!/usr/bin/env node

const {promisify} = require('util');
const fs = require('fs');
const convert = require('heic-convert');
const sharp = require('sharp');

let source = process.argv[2];
let destination = process.argv[3];

function findNext(files, counter) {
    while (files.filter(fn => fn === "jeorg." + counter + ".page.bkg.jpg").length > 0) {
        counter++;
    }
    return counter;
}

function convertFile(src, dst) {
    fs.readdir(src, function (err, files) {
        const filteredFiles = files.filter(fn => fn.endsWith('heic') || fn.endsWith('HEIC'));
        let counter = 1;
        filteredFiles.forEach(sourceHeicFile => {
            counter = findNext(files, counter);
            (async (destJpegFile) => {
                try {
                    const inputBuffer = await promisify(fs.readFile)(src + "/" + sourceHeicFile);
                    const outputBuffer = await convert({
                        buffer: inputBuffer,
                        format: 'JPEG',
                        quality: 1,
                        width: 800,
                        height: 600,
                    });
                    let outputFile = dst + "/" + destJpegFile;
                    let inputFile = dst + "/" + destJpegFile + ".tmp";
                    await promisify(fs.writeFile)(inputFile, outputBuffer);
                    sharp(inputFile).resize({width: 800}).toFile(outputFile)
                        .then(function (newFileInfo) {
                        })
                        .catch(function (err) {
                        });
                } catch (e){

                }
            })("jeorg." + (counter++) + ".page.bkg.jpg");
        });
    });
}

if (source && destination) {
    convertFile(source, destination);
}

module.exports = convertFile