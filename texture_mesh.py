import pymeshlab
ms = pymeshlab.MeshSet()
print('loading rasters')
ms.load_project(['images\BUNDLER.out.bundle.out', 'images\BUNDLER.out.list.txt'])
print('-rasters loaded')

print('loading mesh')
ms.load_new_mesh('mesh.ply')
print('-mesh loaded')

print('starting texturing')
ms.compute_texcoord_parametrization_and_texture_from_registered_rasters(texturesize=1024 ,colorcorrection=True)
print('-texturing done')


ms.save_current_mesh('out.ply')
